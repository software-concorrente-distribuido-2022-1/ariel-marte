import socket

HOST = "localhost"  
PORT = 8080  

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))

nome = str(input("Nome: "))
nivel = str(input("Nivel: "))
salario = str(input("Salario: (999,99)"))
numDep = int(input("Numero de dependentes: "))

req = nome + "|" + nivel + "|" + salario + "|" + str(numDep) + "\n"

s.sendall(req.encode("utf-8"))
data = s.recv(1024)

print(data.decode("utf-8"))