from django.shortcuts import render_to_response
from django.template import RequestContext
from django.http import HttpResponse
from django.utils import simplejson
from datetime import datetime, timedelta
from apps.conta.models import *

def home(request):
    transacoes = Transacao.objects.filter(pago = 0).order_by('data_vencimento')
    entradas = []
    saidas = []
    for transacao in transacoes:
        if transacao.conta.tipo == 0:
            entradas.append(transacao)
        else:
            saidas.append(transacao)    

    #entradas = Conta.objects.filter(tipo=0).order_by('data')
    #saidas = Conta.objects.filter(tipo=1).order_by('data')
    #transacoes_entrada = [("",[])]#([],[])
    #transacoes_saida = ([],[])
    #for entrada in entradas:
     #   transacoes_entrada.append(( entrada.nome, Transacao.objects.filter(pago = 0,conta = entrada)))
        
    #for saida in saidas:
    #    transacoes_saida[1].append(Transacao.objects.filter(pago = 0,conta = saida))
    #    transacoes_saida[0].append(saida.nome)    
    #print transacoes_entrada
    #print entradas
    return render_to_response(
        "pageContas.html",
        { 'entradas': entradas, 'saidas': saidas },
        context_instance=RequestContext(request)
    );

def insert(request):
    conta = Conta()
    conta.nome = request.POST['nome']
    conta.data = datetime.strptime(request.POST['data'], '%d/%m/%Y')
    conta.valor_total = float(request.POST['valor'])
    conta.parcelas = int(request.POST['parcelas'])

    if request.POST['tipo'] == 'saida':
        conta.tipo = 1

    if request.POST['livro'] == 'conta':
        conta.livro = 1

    conta.descricao = request.POST['descricao']
    conta.save()

    for i in xrange(conta.parcelas):
        transacao = Transacao()
        transacao.conta = conta
        transacao.nome = conta.nome + ': Parcela %s' % (i +1)
        transacao.valor = conta.valor_total/conta.parcelas
        transacao.data_vencimento = conta.data + timedelta(i*365/12)
        transacao.save()

    return HttpResponse()


def editarConta(request):
    conta = Conta.objects.filter(id = request.POST['id'])
    transacoes = Transacao.objects.filter(conta = conta)    
    
        
    return render_to_response(
        "popupConta.html",
        { 'conta': conta, 'transacoes': transacoes },
        context_instance=RequestContext(request)
    );

def alterarConta(request):
    print request.POST
    lista_transacoes_pagas = request.POST.getlist('transacoesPagas[]')
        
    id_conta = request.POST['id']
    conta_query = Conta.objects.filter(id = id_conta) 
    #print dir(conta)
    conta = conta_query.get()    
    conta.nome = request.POST['nome']
    conta.data = datetime.strptime(request.POST['data'], '%d/%m/%Y')
    conta.descricao =  request.POST['descricao']
    conta.valor_total = request.POST['valor']
    if request.POST['tipo'] == 'saida':
        conta.tipo = 1
    else:
        conta.tipo = 0
    if request.POST['livro'] == 'conta':
        conta.livro = 1
    else:
        conta.livro = 0
    conta.save()
   
    Transacoes = Transacao.objects.filter(conta = conta_query).order_by('data_vencimento')
    
    
    i = 0
    for transacao in Transacoes:
 

            
        transacao.nome = conta.nome + ': Parcela %s' % (i +1)
        transacao.valor = float(conta.valor_total)/float(conta.parcelas)
        transacao.data_vencimento = conta.data + timedelta(i*365/12)
        print transacao.id
        print type(lista_transacoes_pagas)
        print str(transacao.id) in lista_transacoes_pagas 
        if str(transacao.id) in lista_transacoes_pagas:
            transacao.pago = 1        

        transacao.save()
        i = i + 1;
    return HttpResponse()
