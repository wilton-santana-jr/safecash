from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render_to_response
from django.template import RequestContext

from apps.home.models import *

def login (request):
    return render_to_response("login.html")
    
#def check_login (request):
#    return render_to_response("login.html")
