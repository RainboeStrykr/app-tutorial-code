with open("data.txt") as f:
    lines = [line.strip() for line in f if line.strip()]

numbers = list(map(int, lines[1].split()))
divisible = list(filter(lambda x: x % 3 == 0 and x % 5 == 0, numbers))
print("All Numbers:", numbers)
print("Numbers Divisible by both 3 and 5:", divisible)
