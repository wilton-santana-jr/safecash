from django.shortcuts import render_to_response
from django.template import RequestContext
from django.http import HttpResponse
from django.utils import simplejson
from datetime import datetime

def home(request):
    entradas = [{'data': datetime.now(), 'descricao': ' modelo', 'valor': 20.25}]
    saidas = [{'data': datetime.now(), 'descricao': ' m', 'valor': 11.4}]

    return render_to_response(
        "pageContas.html",
        { 'entradas': entradas, 'saidas': saidas },
        context_instance=RequestContext(request)
    );

def insert(request):
    print request.POST['tipo']
    return HttpResponse()
