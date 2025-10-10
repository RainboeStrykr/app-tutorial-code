numbers = [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13]

is_prime = lambda n: n > 1 and all(n % i != 0 for i in range(2, int(n**0.5) + 1))

primes = list(filter(is_prime, numbers))

print("Prime numbers:", primes)
