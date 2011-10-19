from django.shortcuts import render_to_response
from django.template import RequestContext
from django.http import HttpResponse
from django.utils import simplejson

def home(request):
    todos = [{'id': 10, 'nome': 'meu primeiro modelo'}]

    return render_to_response(
        "pageModelos.html",
        { 'modelos': todos },
        context_instance=RequestContext(request)
    );

def get_more_info(request):
    print 'modelo requisitado: ' + request.POST['id']
    descricao = 'descricao do modelo requisitado'
    toSend = { 'desc' : descricao }
    return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
