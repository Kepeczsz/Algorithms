from sklearn.base import BaseEstimator, ClassifierMixin
import numpy as np


class SimplePerceptron(BaseEstimator, ClassifierMixin):

    def __init__(self, learning_rate=1.0, maxIter = 0):
        self.learning_rate_ = learning_rate
        self.w_ = None  # vector wag
        self.k_ = None  # licznik krokow
        self.class_labels_ = None  # wykaz etykiet klass
        self.maxIter = maxIter
    def fit(self, X, y, max_iter=2000):
        self.class_labels_ = np.unique(
            y)  # zakladamy, ze istnieja dokladnie 2 klasy  self.class_labels_[0] = -1 , self.class_labels_[1] = 1
        m, n = X.shape
        yy = np.ones(m, dtype=np.int)
        yy[y == self.class_labels_[0]] = -1

        self.k_ = 0
        self.w_ = np.zeros(n + 1)
        X_extended = np.c_[np.ones(m), X]  # zeby usunac dopisywania jedynek
        while True:
            E = []  # lista indexow blednie sklasyfikowanych punktow
            for i in range(m):  # przeiteruj po danych i sie dowiedz ktore sie dobrze klasyfikuja
                s = self.w_.dot(X_extended[i])
                f = 1 if s > 0 else -1
                if f != yy[i]:
                    E.append(i)
            if len(E) == 0 or self.k_ == self.maxIter:
                return

            i = np.random.choice(E)  # bierze randomowego indexa z listy E
            self.w_ = self.w_ + self.learning_rate_ * yy[i] * X_extended[i]
            self.k_ += 1



    def predict(self, X):
        sums = self.decision_function(X)
        m = X.shape[0]
        predictions = np.empty(m, dtype=self.class_labels_.dtype)
        predictions[sums > 0.0] = self.class_labels_[1]
        predictions[sums <= 0.0] = self.class_labels_[0]
        return predictions

    def decision_function(self, X):
        m = X.shape[0]
        return self.w_.dot(np.c_[np.ones(m), X].T)  # zwracamy sumy wazone
