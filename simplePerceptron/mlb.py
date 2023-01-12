import numpy as np
import matplotlib.pyplot as plt
from perceptron import SimplePerceptron


def fake_data2(m):
    X = np.random.rand(m) * (2 * np.pi)
    y = (np.random.rand(m) * 2) - 1
    X = np.c_[X, y]
    yi = []

    for i in range(X.shape[0]):
        if np.abs(np.sin(X[i][0])) > np.abs(X[i][1]):
            yi.append(-1)
        else:
            yi.append(1)

    for i in range(X.shape[0]):
        X[i][0] = (X[i][0] / (np.pi)) - 1

    return X, yi

def distToCent(X, c, sigma):
    Z = np.zeros(shape=(X.shape[0], c.shape[0]))
    for i in range(len(c)):
        Z[:, i] = np.exp((-np.sum(np.power(np.subtract(X, c[i]), 2), axis=1)) / (2 * np.power(sigma, 2)))
    return Z

if __name__ == '__main__':
    np.random.seed(0)
    m = 1000
    centra = 100
    X, y = fake_data2(m)

    clf = SimplePerceptron(learning_rate=1.0, maxIter= 2000)
    centraM = np.random.uniform(low=-1.0, high=1.0, size=(centra, 2))
    sigma = 0.2
    Z = distToCent(X, centraM, sigma)
    clf.fit(Z, y)

    x_mesh = np.linspace(0, 2 * np.pi, m)
    y_mesh = np.linspace(-1, 1, m)
    xx, yy = np.meshgrid(x_mesh, y_mesh)
    X2 = np.c_[xx.ravel(), yy.ravel()]

    y2 = np.ones(len(X2))
    x1 = np.abs(np.sin(X2[:, 0]))
    x2 = np.abs(X2[:, 1])
    y2[x1 > x2] = -1

    X_max = np.max(xx, 1)
    X_min = np.min(xx, 1)
    xx = (xx - X_min) / (X_max - X_min)
    xx = xx * (1 - (-1)) + (-1)

    X2_norm = np.c_[xx.ravel(), yy.ravel()]
    z = distToCent(X2_norm, centraM, sigma)
    predictions = clf.predict(z)
    acc = np.mean(predictions == y2)
    predictions = predictions.reshape(xx.shape)

    print("ACC: ", str(acc))
    print("Sigma: ", str(sigma))

    plt.pcolormesh(xx, yy, predictions, cmap="BuGn", shading='auto')
    plt.scatter(X[:, 0], X[:, 1], c=y,cmap = "coolwarm", s=10, facecolor='none')
    plt.scatter(centraM[:, 0], centraM[:, 1], s=10, facecolor='black')

    plt.show()
