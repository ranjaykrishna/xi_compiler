import random

x = []

for i in range(10):
    x.append('x%i' % i)

print "main(args:int[][]) : int {"

for a in x:
    print "  %s:int = 0" % a

for i in range(30):
    a = x[int(random.random() * len(x))]
    b = x[int(random.random() * len(x))]
    c = x[int(random.random() * len(x))]
    print "  %s = %s + %s" % (a, b, c)
print "  return %s" % str(' + '.join(x))

print "}"
