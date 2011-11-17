from django.shortcuts import render_to_response, get_object_or_404, redirect
from django.template import RequestContext
from apps.requisicao.models import *
from django.utils import simplejson
from django.http import HttpResponse
import datetime

def home(request):
  if  not request.user.is_authenticated():
	return redirect('/')
  requisicoes = Requisicao.objects.all()
  contr = RequisicaoContrato.objects.all()
  out = RequisicaoReembolso.objects.all()
  return render_to_response("pageRequisicao.html", {"requisicoes": requisicoes}, context_instance=RequestContext(request))
  

def visualizar(request):
  if  not request.user.is_authenticated():
	return redirect('/')
  source = request.POST.get('source')
  print "SOURCE" + source
  requisicao = get_object_or_404(Requisicao.objects.all(),id=request.POST.get('chave'))
  print requisicao
  toSend = { 'descricao' : requisicao.descricao}
  '''if source == 'geral' :
    print 'geral'
    reembolso = get_object_or_404(RequisicaoReembolso.objects.all(),requisicao=requisicao)
    print reembolso
    toSend = { 'descricao' : reembolso.requisicao.descricao }
  else:
    print 'contrato'
    contrato = get_object_or_404(RequisicaoContrato.objects.all(),requisicao=requisicao)
    print contrato
    toSend = { 'descricao' : requisicao.descricao, 'nomeProjeto' : contrato.nome_projeto, 'contratante' : contrato.responsavel, 
	'cpf_cnpj' : contrato.cpf_cnpj, 'valor' : requisicao.valor, 'observacao' : contrato.observacao, 'url': contrato.endereco }'''
	
  return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
  
def salvarEstado(request):
  if  not request.user.is_authenticated():
	return redirect('/')
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

def remove(request):
    if not request.user.is_authenticated(): 
        return redirect('/')
    print 'remover'
    print request.POST.get('chave')
    m = get_object_or_404(Requisicao.objects.all(), id=request.POST.get('chave'))
  
    m.delete()

    return redirect('apps.requisicao.views.home') 
  
  
  
def inserirReq(request):
    if  not request.user.is_authenticated():
	    return redirect('/')
    
    if request.method == 'POST':
		
        assunto = request.POST.get('assunto',"")
        descricao = request.POST.get('descricao',"")
        data = request.POST.get('data',"")
        valor = float(request.POST.get('valor',0.0))
        tipo = int(request.POST.get('tipo',0))
        status = request.POST.get('status',"")
        
		#criar o tipo de requisicao
        requisicao = Requisicao()
        requisicao.usuario = request.user
        requisicao.assunto = assunto
        requisicao.descricao = descricao
        requisicao.data =  datetime.datetime.strptime(data, "%d/%m/%Y")
        requisicao.status = status
        requisicao.tipo = tipo
        requisicao.valor = valor
        requisicao.save()
        print tipo
        '''if tipo is 0:
            print 'reembolso'
            reembolso = RequisicaoReembolso()
            reembolso.requisicao = requisicao
            reembolso.save()
            print 'salvou'
        else:
            print 'contrato'
            contrato = RequisicaoContrato()
            contrato.requisicao = requisicao
            contrato.save()'''
    return HttpResponse()
