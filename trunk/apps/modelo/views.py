from django.shortcuts import render_to_response, get_object_or_404
from django.template import RequestContext
from django.http import HttpResponse, HttpResponseRedirect
from django.utils import simplejson
from apps.modelo.models import Modelo


def home(request):
    
    modelos = Modelo.objects.all()
	
    return render_to_response(
        "pageModelos.html",
        { 'modelos': modelos },
        context_instance=RequestContext(request)
    )

def get_more_info(request):
    m = get_object_or_404(Modelo.objects.all(), id=request.POST.get('id'))
    toSend = { 'desc' : m.descricao }
    return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
    
def remove(request):
    m = get_object_or_404(Modelo.objects.all(), id=request.POST.get('id'))
    m.delete()
    return HttpResponseRedirect('/modelos/')
    
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
    

    
    
    
    
    
    
