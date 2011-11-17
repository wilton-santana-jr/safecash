# Create your views here.
from django.shortcuts import render_to_response, get_object_or_404
from django.template import RequestContext
from apps.livro.models import *
from apps.conta.models import *
import datetime

def home(request):
  # livro = 0 => Conta
  # livro = 1 => Caixa
  # tipo = 0 => Entrada
  # tipo = 1 => Saida
  # pago = 0 => pendente
  # pago = 1 => paga
 
  data_atual = datetime.date.today()
  mes_atual = data_atual.month
  ano_atual = data_atual.year
  
  meses = {'janeiro': 1,
           'fevereiro': 2,
           'marco' :3,
           'abril': 4,
           'maio': 5,
           'junho': 6,
           'julho': 7,
           'agosto': 8,
           'setembro': 9,
           'outubro': 10,
           'novembro': 11,
           'dezembro': 12}

  # Post do filtro de mes e ano.
  if request.method == "POST":    
    mes = request.POST.get("mes")
    ano = request.POST.get("ano")

    if mes != '' and ano != '':
      mes_atual = meses[mes]
      ano_atual = ano
  
  ##############
  
  livro_conta_entrada = Conta.objects.filter(livro=0, tipo=0)
  transacoes_conta_entrada = []

  for conta in livro_conta_entrada:
    transacoes = Transacao.objects.filter(conta=conta, data_vencimento__month=mes_atual, data_vencimento__year=ano_atual, pago=1).order_by('data_vencimento')

    for transacao in transacoes:     
      transacoes_conta_entrada += [transacao]

  ##############

  livro_conta_saida = Conta.objects.filter(livro=0, tipo=1)
  transacoes_conta_saida = []
  
  for conta in livro_conta_saida:
    transacoes = Transacao.objects.filter(conta=conta, data_vencimento__month=mes_atual, data_vencimento__year=ano_atual, pago=1).order_by('data_vencimento')
    
    for transacao in transacoes:
      transacoes_conta_saida += [transacao]

  ##############

  livro_caixa_entrada = Conta.objects.filter(livro=1, tipo=0)
  transacoes_caixa_entrada = []
  
  for conta in livro_caixa_entrada:
    transacoes = Transacao.objects.filter(conta=conta, data_vencimento__month=mes_atual, data_vencimento__year=ano_atual, pago=1).order_by('data_vencimento')
    
    for transacao in transacoes:
      transacoes_caixa_entrada += [transacao]

  ##############

  livro_caixa_saida = Conta.objects.filter(livro=1, tipo=1)
  transacoes_caixa_saida = []
  
  for conta in livro_caixa_saida:
    transacoes = Transacao.objects.filter(conta=conta, data_vencimento__month=mes_atual, data_vencimento__year=ano_atual, pago=1).order_by('data_vencimento')
    
    for transacao in transacoes:
      transacoes_caixa_saida += [transacao]

  return render_to_response(
      "PageLivro.html", 
      {"transacoes_conta_entrada": transacoes_conta_entrada, 
       "transacoes_conta_saida": transacoes_conta_saida,
       "transacoes_caixa_entrada": transacoes_caixa_entrada, 
       "transacoes_caixa_saida": transacoes_caixa_saida}, 
      context_instance=RequestContext(request));
