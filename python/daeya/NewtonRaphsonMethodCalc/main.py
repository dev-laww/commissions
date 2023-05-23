import math


def f(v, h, r):
    return math.sqrt(v) - (2 * math.sqrt(2 * math.pow(h, 3 / 2)) / r)


def f_derivative(v, h, r):
    return -2 * math.sqrt(2 * math.pow(h, 3 / 2)) / (r * r)


def newton_raphson(v, h, x0, tolerance, max_iterations):
    x = x0
    iteration = 0

    # Print table header
    print('| {:^5} | {:^10} | {:^13} | {:^14} | {:^11} |'.format(
        'i',
        'h & v',
        "   f'(x)   ",
        "   f'(x+1)   ",
        "    x+1    "
    ))

    while iteration < max_iterations:
        f_prime = f_derivative(v, h, x)

        # Check for zero derivative
        if f_prime == 0:
            print('Zero derivative encountered. Exiting...')
            return None

        x_next = x - f(v, h, x) / f_prime

        # Print table values for each iteration
        print('| {:^5} | {:^4.2f}, {:^4.2f} | {:^13.4f} | {:^14.4f} | {:^11.6f}'.format(
            iteration + 1,
            h,
            v,
            f_prime,
            f_derivative(v, h, x_next),
            x_next
        ))

        if abs(x_next - x) < tolerance:
            return x_next

        x = x_next
        iteration += 1

    return None


def main():
    print('Newton-Raphson Method Calculator for Finding the Radius of a Cylinder Tank')
    print('Equation: R * sqrt(v / (2 * h))')
    print('Derived Equation: sqrt(v) - (2 * sqrt(2 * h^3/2) / R)')

    h = float(input('Enter the height (h) of the cylinder tank: '))
    v = float(input('Enter the volume (v) of the cylinder tank: '))
    x0 = float(input('Enter the initial guess (x0) for the radius: '))
    tolerance = float(input('Enter the tolerance: '))
    max_iterations = int(input('Enter the maximum number of iterations: '))

    radius = newton_raphson(v, h, x0, tolerance, max_iterations)

    if radius is not None:
        print('\nRadius of the cylinder tank is:', radius)
        return

    print('\nRadius not found within the given tolerance and maximum iterations.')


if __name__ == '__main__':
    main()
    input()
