from functools import reduce
import math

with open("data.txt") as f:
    lines = [line.strip() for line in f if line.strip()]

numbers = list(map(int, lines[1].split()))
gcd_result = reduce(lambda a, b: math.gcd(a, b), numbers)
print("Numbers:", numbers)
print("GCD of all numbers:", gcd_result)
