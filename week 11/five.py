from pyDatalog import pyDatalog

pyDatalog.clear()
pyDatalog.create_terms('parent, grandparent, X, Y, Z')

# Facts
+parent('John', 'Mary')
+parent('Mary', 'Sam')
+parent('Robert', 'John')
+parent('Linda', 'John')

# Rule: X is grandparent of Z if X is parent of Y and Y is parent of Z
grandparent(X, Z) <= parent(X, Y) & parent(Y, Z)

# Query
print(grandparent(X, Z))
