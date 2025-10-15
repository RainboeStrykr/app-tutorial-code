with open("data.txt") as f:
    lines = [line.strip() for line in f if line.strip()]

numbers = list(map(int, lines[1].split()))
is_prime = lambda n: n > 1 and all(n % i != 0 for i in range(2, int(n**0.5) + 1))
primes = list(filter(is_prime, numbers))
print("All Numbers:", numbers)
print("Prime Numbers:", primes)
