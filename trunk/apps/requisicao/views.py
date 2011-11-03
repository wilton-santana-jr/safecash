from django.shortcuts import render_to_response, get_object_or_404
from django.template import RequestContext
from apps.requisicao.models import *
from django.utils import simplejson
from django.http import HttpResponse
import datetime


def home(request):
  requisicoes = Requisicao.objects.all()
  print requisicoes
  return render_to_response("pageRequisicao.html", {"requisicoes": requisicoes}, context_instance=RequestContext(request))
  

def visualizar(request):
  source = request.POST.get('source')
  requisicao = get_object_or_404(Requisicao.objects.all(),id=request.POST.get('chave'))
	
  if source == 'geral' :
	reembolso = get_object_or_404(RequisicaoReembolso.objects.all(),requisicao=requisicao)
	toSend = { 'descricao' : reembolso.requisicao.descricao }
	
  else:
	contrato = get_object_or_404(RequisicaoContrato.objects.all(),requisicao=requisicao)
	toSend = { 'descricao' : requisicao.descricao, 'nomeProjeto' : contrato.nome_projeto, 'contratante' : contrato.responsavel, 
	'cpf_cnpj' : contrato.cpf_cnpj, 'valor' : requisicao.valor, 'observacao' : contrato.observacao }
	
  return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
  
def salvarEstado(request):

  requisicao = get_object_or_404(Requisicao.objects.all(),id=request.POST.get('chave'))
  estado = request.POST.get("status")
  requisicao.status = '3'
  toSend = { 'id' : requisicao.id, 'estado' : "PENDENTE",  'classe' : "estado pendente" }
  if estado == 'OK':
	requisicao.status = '1'
	toSend = { 'id' : requisicao.id, 'estado' : "OK",  'classe' : "estado ok" }
  elif estado == 'Pronto':
	requisicao.status = '2'
	toSend = { 'id' : requisicao.id, 'estado' : "PRONTO",  'classe' : "estado pronto" }
  requisicao.save()
  return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
  
def inserirReq(request):
    print request.POST
    if request.method == 'POST':
       # user = User()
        #user.name = "teste"
        #user.save()
        user =  User.objects.get(username="teste")
        print user.id
        assunto = request.POST.get('assunto',"")
        descricao = request.POST.get('descricao',"")
        data = request.POST.get('data',"")
        valor = float(request.POST.get('valor',0.0))
        tipo = int(request.POST.get('tipo',0))
        status = request.POST.get('status',"")
               
        requisicao = Requisicao()
        requisicao.usuario = user
        requisicao.assunto = assunto
        requisicao.descricao = descricao
        requisicao.data =  datetime.datetime.strptime(data, "%d/%m/%Y")
        requisicao.status = status
        requisicao.tipo = tipo
        requisicao.valor = valor
        
        requisicao.save()
        print requisicao.id
   
    return HttpResponse()
    
