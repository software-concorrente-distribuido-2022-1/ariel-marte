from xmlrpc.server import SimpleXMLRPCServer

def calcularValorCrediro(saldoMedio : float):
    valorCredito = float()
    percentualCredito = int()

    if(saldoMedio <= 200):
        return 0
    
    if(saldoMedio > 200 and saldoMedio <= 400):
        percentualCredito = 20
    if(saldoMedio > 400 and saldoMedio <= 600):
        percentualCredito = 30
    if(saldoMedio > 600):
        percentualCredito = 40
    
    valorCredito = saldoMedio*percentualCredito/100
    
    return valorCredito

server = SimpleXMLRPCServer(("127.0.0.1", 4000))
print("Listening on port 4000...")
server.register_function(calcularValorCrediro, 'calcularValorCrediro')

server.serve_forever()