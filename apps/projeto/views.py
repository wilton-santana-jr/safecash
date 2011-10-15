# Create your views here.
from django.shortcuts import render_to_response, get_object_or_404
from django.template import RequestContext


def projeto(request):
  
  return render_to_response("PageProjetos.html", context_instance=RequestContext(request));
