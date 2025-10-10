from pyDatalog import pyDatalog
from datetime import date

pyDatalog.clear()
pyDatalog.create_terms('employee, hired_before, X, Y, Y2')

# Facts: employee(Name, HireDate)
+employee('Alice', date(2020, 5, 1))
+employee('Bob', date(2021, 3, 15))
+employee('Charlie', date(2019, 12, 10))
+employee('David', date(2022, 1, 25))

# Rule: hired_before(X, Y) means employee X was hired before date Y
hired_before(X, Y) <= (employee(X, Y2) & (Y2 < Y))

# Query: employees hired before 2021-01-01
print(hired_before(X, date(2021, 1, 1)))
