print("first")


def say_hello():
    str = "hello"
    print(str)
    print(__name__+'from hello.sayhello()')


if __name__ == "__main__":
    print ('This is main of module "hello.py"')
    say_hello()
    print(__name__+'from hello.main')
