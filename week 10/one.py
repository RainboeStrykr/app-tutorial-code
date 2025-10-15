with open("data.txt") as f:
    lines = [line.strip() for line in f if line.strip()]

celsius = list(map(float, lines[0].split()))
fahrenheit = list(map(lambda c: (c * 9/5) + 32, celsius))
print("Temperatures in Celsius:", celsius)
print("Temperatures in Fahrenheit:", fahrenheit)
