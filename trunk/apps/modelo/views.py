from django.shortcuts import render_to_response, get_object_or_404, redirect
from django.template import RequestContext
from django.http import HttpResponse
from django.utils import simplejson
from apps.modelo.models import Modelo
import uuid

def home(request):
    modelos = Modelo.objects.all()
	
    return render_to_response(
        "pageModelos.html",
        { 'modelos': modelos },
        context_instance=RequestContext(request)
    )

def get_more_info(request):
    m = get_object_or_404(Modelo.objects.all(), id=request.POST.get('id'))
    toSend = { 'desc' : m.descricao, 'filename': m.arquivo }
    return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
    
def remove(request):
    m = get_object_or_404(Modelo.objects.all(), id=request.POST.get('id'))
    m.delete()

    return redirect('apps.modelo.views.home') 

def get_filename(filename):
    ext = filename.split('.')[-1]
    filename = "%s.%s" % (uuid.uuid4(), ext)
    return filename

def insert(request):
    arquivo = request.FILES['endereco']
    filename = get_filename(arquivo.name)
    path = 'media/arquivos/' + filename 
    
    destination = open(path, 'wb+')

    for chunk in arquivo.chunks():
        destination.write(chunk)

    destination.close()
    
    modelo = Modelo()
    modelo.nome = request.POST['nome']
    modelo.descricao = request.POST['descricao']
    modelo.arquivo = filename 
    modelo.save()

    return redirect('apps.modelo.views.home') 
