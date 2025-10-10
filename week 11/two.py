from pyDatalog import pyDatalog

pyDatalog.clear()
pyDatalog.create_terms('path, edge, path_length, X, Y, Z, L, L2')

# Facts
+edge('A', 'B')
+edge('B', 'C')
+edge('A', 'D')
+edge('D', 'C')
+edge('C', 'E')

# Path rules with length
path_length(X, Y, 1) <= edge(X, Y)
path_length(X, Y, L) <= edge(X, Z) & path_length(Z, Y, L2) & (L == L2 + 1)

# Simple reachability
path(X, Y) <= edge(X, Y)
path(X, Y) <= edge(X, Z) & path(Z, Y)

# Queries
print("All reachable nodes from A:")
print(path('A', Y))
print("\nPath lengths from A to E:")
print(path_length('A', 'E', L))
