numbers = [10, 15, 30, 45, 50, 60, 75, 100]

divisible = list(filter(lambda x: x % 3 == 0 and x % 5 == 0, numbers))

print("Numbers divisible by both 3 and 5:", divisible)
