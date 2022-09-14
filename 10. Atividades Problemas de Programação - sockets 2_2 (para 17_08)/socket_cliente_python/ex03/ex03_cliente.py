import socket

HOST = "localhost"  
PORT = 8080  

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

print("Insira as notas no formato 9.99. Se o aluno não realizou a prova inserir 0. \n");

n1 = float(input("Nota 1: "))
n2 = float(input("Nota 2: "))
n3 = float(input("Nota 3: "))

req = "{:05.2f}".format(n1) + "|" + "{:05.2f}".format(n2) + "|" + "{:05.2f}".format(n3) + "\n"

s.sendall(req.encode("utf-8"))
data = s.recv(1024)

print(data.decode("utf-8"))