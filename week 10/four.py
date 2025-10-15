with open("data.txt") as f:
    lines = [line.strip() for line in f if line.strip()]

numbers = list(map(int, lines[1].split()))
added = list(map(lambda x: x + 10, numbers))
print("Original Numbers:", numbers)
print("After Adding 10:", added)
