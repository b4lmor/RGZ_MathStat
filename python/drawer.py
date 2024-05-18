import numpy as np
import matplotlib.pyplot as plt

sample = ("0.277 0.690 0.233 0.799 0.400 0.105 0.542 0.633 0.652 0.953 0.725 0.224 0.769 0.269 0.458 0.191 0.777 0.533 "
          "0.658 0.444 0.636 0.332 0.976 0.616 0.083 0.039 0.406 0.002 0.056 0.318")


def empirical_cdf_plot(data):
    data = np.array(data)
    sorted_data = np.sort(data)
    n = len(data)

    def ecdf(value):
        return np.searchsorted(sorted_data, value, side='right') / n

    x = np.linspace(min(data), max(data), num=1000)
    y = np.array([ecdf(i) for i in x])

    plt.figure(figsize=(8, 5))
    plt.step(x, y)
    plt.title('Empirical CDF')
    plt.xlabel('x')
    plt.ylabel('F*(x)')
    plt.grid(True)
    plt.show()


if __name__ == '__main__':
    empirical_cdf_plot(list(map(float, sample.split())))
