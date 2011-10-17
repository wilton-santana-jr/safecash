from django.shortcuts import render_to_response
from django.template import RequestContext
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
    return HttpResponse(simplejson.dumps({'oi': '11'}), minetype='application/javascript')
