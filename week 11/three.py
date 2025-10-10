from pyDatalog import pyDatalog

pyDatalog.clear()
pyDatalog.create_terms('student, subject, marks, passed_all, X, Y, Z')

# Facts
+marks('Riya', 'Math', 85)
+marks('Riya', 'Science', 78)
+marks('Riya', 'English', 92)

# Rule: A student passes a subject if marks >= 50
passed_all(X) <= ~(marks(X, Y, Z) & (Z < 50))

# Query
print(passed_all(X))
