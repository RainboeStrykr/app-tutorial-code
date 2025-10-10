from pyDatalog import pyDatalog

pyDatalog.clear()
pyDatalog.create_terms('employee, student, both, X')

# Facts
+employee('Alice')
+employee('Bob')
+employee('Charlie')
+student('Bob')
+student('David')
+student('Charlie')

# Rule: a person is both an employee and a student
both(X) <= employee(X) & student(X)

# Query
print(both(X))
