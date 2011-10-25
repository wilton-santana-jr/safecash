from django.shortcuts import render_to_response
from django.template import RequestContext
from django.http import HttpResponse
from django.utils import simplejson
from datetime import datetime, timedelta
from apps.conta.models import *

def home(request):
    entradas = Conta.objects.filter(tipo=0).order_by('data')
    saidas = Conta.objects.filter(tipo=1).order_by('data')

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
        transacao.nome = conta.nome + ': Parcela %s' % i
        transacao.valor = conta.valor_total/conta.parcelas
        transacao.data_vencimento = conta.data + timedelta(i*365/12)
        transacao.save()

    return HttpResponse()
