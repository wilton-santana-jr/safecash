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
    
    mes_atual = meses[mes]
    ano_atual = ano
  
  #livro_conta_entrada = Conta(nome="Conta Entrada", data=datetime.datetime.now(), livro=0, tipo=0)#Conta.objects.filter(tipo=0, livro=0)
  #livro_conta_entrada.save()
  livro_conta_entrada = Conta.objects.filter(livro=0, tipo=0)
  transacoes_conta_entrada = Transacao.objects.filter(conta=livro_conta_entrada, data_vencimento__month=mes_atual, data_vencimento__year=ano_atual, pago=1).order_by('data_vencimento')

  #livro_conta_saida = Conta(nome="Conta Saida", data=datetime.datetime.now(), livro=0, tipo=1)#Conta.objects.filter(tipo = 0, livro=1)
  #livro_conta_saida.save()
  livro_conta_saida = Conta.objects.filter(livro=0, tipo=1)
  transacoes_conta_saida = Transacao.objects.filter(conta=livro_conta_saida, data_vencimento__month=mes_atual, data_vencimento__year=ano_atual, pago=1).order_by('data_vencimento')

  #livro_caixa_entrada = Conta(nome="Caixa Entrada", data=datetime.datetime.now(), livro=1, tipo=0)#Conta.objects.filter(tipo=1, livro=0)
  #livro_caixa_entrada.save()
  livro_caixa_entrada = Conta.objects.filter(livro=1, tipo=0)
  transacoes_caixa_entrada = Transacao.objects.filter(conta=livro_caixa_entrada, data_vencimento__month=mes_atual, data_vencimento__year=ano_atual, pago=1).order_by('data_vencimento')
  
  #livro_caixa_saida = Conta(nome="Caixa Saida", data=datetime.datetime.now(), livro=1, tipo=1)#Conta.objects.filter(tipo=1, livro=1) 
  #livro_caixa_saida.save()
  livro_caixa_saida = Conta.objects.filter(livro=1, tipo=1)
  transacoes_caixa_saida = Transacao.objects.filter(conta=livro_caixa_saida, data_vencimento__month=mes_atual, data_vencimento__year=ano_atual, pago=1).order_by('data_vencimento')

  return render_to_response(
      "PageLivro.html", 
      {"transacoes_conta_entrada": transacoes_conta_entrada, 
       "transacoes_conta_saida": transacoes_conta_saida,
       "transacoes_caixa_entrada": transacoes_caixa_entrada, 
       "transacoes_caixa_saida": transacoes_caixa_saida}, 
      context_instance=RequestContext(request));

def povoar_banco(request):
  a = Conta.objects.get(livro=0, tipo=0)
  t = Transacao(nome='Transacao Conta Entrada', valor=10.00, conta=a, data_vencimento=datetime.datetime.now(), pago=1)
  t1 = Transacao(nome='Conta Entrada', valor=25.00, conta=a, data_vencimento=datetime.datetime.now(), pago=1)
  t.save()
  t1.save()
  
  b = Conta.objects.get(livro=0, tipo=1)
  t = Transacao(nome='Transacao Conta Saida', valor=10.00, conta=b, data_vencimento=datetime.datetime.now(), pago=1)
  t1 = Transacao(nome='Conta Saida', valor=33.90, conta=b, data_vencimento=datetime.datetime.now(), pago=1)
  t.save()
  t1.save()
  
  c = Conta.objects.get(livro=1, tipo=0)
  t = Transacao(nome='Transacao Caixa Entrada', valor=10.00, conta=c, data_vencimento=datetime.datetime.now(), pago=1)
  t1 = Transacao(nome='Caixa Entrada', valor=58.00, conta=c, data_vencimento=datetime.datetime.now(), pago=1)
  t.save()
  t1.save()

  d = Conta.objects.get(livro=1, tipo=1)
  t = Transacao(nome='Transacao Caixa Saida', valor=10.00, conta=d, data_vencimento=datetime.datetime.now(), pago=1)
  t1 = Transacao(nome='Caixa Saida', valor=190.00, conta=d, data_vencimento=datetime.datetime.now(), pago=1)
  t.save()
  t1.save()
