from functools import reduce
import math

numbers = [24, 60, 36]

gcd_result = reduce(lambda a, b: math.gcd(a, b), numbers)

print("GCD of the list:", gcd_result)
