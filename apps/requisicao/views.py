from django.shortcuts import render_to_response, get_object_or_404
from django.template import RequestContext
from apps.requisicao.models import *
from django.utils import simplejson
from django.http import HttpResponse

def home(request):
  requisicoes = Requisicao.objects.all()
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