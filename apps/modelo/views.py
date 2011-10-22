from django.shortcuts import render_to_response
from django.template import RequestContext
from django.http import HttpResponse, HttpResponseRedirect
from django.utils import simplejson
from apps.modelo.models import Modelo


def home(request):
    todos = [{'id': 10, 'nome': 'meu primeiro modelo'}]

    return render_to_response(
        "pageModelos.html",
        { 'modelos': todos },
        context_instance=RequestContext(request)
    )

def get_more_info(request):
    print 'modelo requisitado: ' + request.POST['id']
    descricao = 'descricao do modelo requisitado'
    toSend = { 'desc' : descricao }
    return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
    
def remove(request):
    id = request.POST['id']
    print 'ENTROU NO REMOVE'
    # MANDAR DELETAR NO BANCO
    toSend = { 'code' : 'error' }
    return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
    
def download(request):
    arquivo = Modelo.filter(id)
    url = "http://www.google.com"
    print 'ENTROU NO DOWNLOAD'
    toSend = { 'url' : url }
    return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
    
    
# FEITO 
def insert(request):
    #print 'NOME: ' + request.POST['nome']    
    #print 'ENDERECO DO ARQUIVO: ' + request.POST['endereco']   
    #print 'DESCRICAO: ' + request.POST['descricao'] 
    arquivo = request.FILES['endereco']
    path = 'media/arquivos/' + arquivo.name
    
    destination = open(path, 'wb+')
    for chunk in arquivo.chunks():
        destination.write(chunk)
    destination.close()
    
    
    modelo = Modelo()

    modelo.nome = request.POST['nome']
    modelo.descricao = request.POST['descricao']
    modelo.arquivo = path
    modelo.save()

    return HttpResponseRedirect('/modelos/')
    

    
    
    
    
    
    
