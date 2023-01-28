import random
from queue import Queue

from AG import AlgorytmGenetyczny
import numpy as np
import threading


def random_dkp(n, scale=10, seed=0):
    np.random.seed(seed)
    items = np.ceil(scale * np.random.rand(n, 2)).astype("int32")
    C = int(np.ceil(0.5 * 0.5 * n * scale))
    v = items[:, 0]
    c = items[:, 1]
    return v, c, C, items


def solve_dkp_exact(v, c, C):
    n = v.size  # liczba przedmiotow
    a = np.zeros((1 + C, n))  # wartosci najlepszych upakowan
    b = np.empty((1 + C, n), dtype=object)  # back-pointery
    for i in range(1, 1 + C):
        if c[0] <= i:
            a[i, 0] = v[0]
            b[i, 0] = (-1, -1)
        else:
            a[i, 0] = 0
            b[i, 0] = (i, -1)
        for j in range(1, n):
            a[i, j] = a[i, j - 1]
            b[i, j] = (i, j - 1)
            if c[j] <= i:
                best_pack_with_j = a[i - c[j], j - 1] + v[j]
                if best_pack_with_j > a[i, j]:
                    a[i, j] = best_pack_with_j
                    b[i, j] = (i - c[j], j - 1)
    solution = np.zeros(n, dtype=np.int8)
    i, j = C, n - 1
    while i > 0 and j >= 0:
        i_new, j_new = b[i, j]
        if i_new < i:
            solution[j] = 1
        i, j = i_new, j_new
    return a[C, n - 1], solution


if __name__ == "__main__":





    # Testy na multithreadingu
    # n = 15
    # tests = 5
    # output_queue1 = Queue()
    # output_queue2 = Queue()
    # for i in range(tests):
    #     v, c, C, items = random_dkp(n, scale=2000,seed = i)
    #     best_pack, solution = solve_dkp_exact(v, c, C)
    #     standardOption = AlgorytmGenetyczny(n, v=v, c=c, capacity=C,m = 2000)
    #     modifiedOption = AlgorytmGenetyczny(n,v=v,c=c,capacity=C,m = 2000 ,doubleCrossOver=True,rank_select=True,fitFunc=True)
    #     thread1 = threading.Thread(target=standardOption.algo_gen_thread,args=(output_queue1,))
    #     thread2 = threading.Thread(target=modifiedOption.algo_gen_test_thread,args=(output_queue2,))
    #     thread1.start()
    #     thread2.start()
    #     thread1.join()
    #     thread2.join()
    #
    #     output1 = output_queue1.get()
    #     output2 = output_queue2.get()
    #     score1 = 0
    #     score2 = 0
    #     for j in range(n):
    #         if(solution[j] == output1[j]):
    #             score1 +=1
    #         if (solution[j] == output2[j]):
    #             score2 += 1
    #
    #     print("ACC1: ", score1/n)
    #     print("ACC2: ", score2/n)




