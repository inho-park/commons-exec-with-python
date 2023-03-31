import sys

def sum(v1, v2):
    print(v1 + v2)

def main(argv):
    sum(argv[1], argv[2])

if __name__ == "__main__":
    main(sys.argv)

for i in range(1, 100):
    print(i)