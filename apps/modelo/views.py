from django.shortcuts import render_to_response
from django.template import RequestContext

def home(request):

  return render_to_response(
          "pageModelos.html",
          { },
          context_instance=RequestContext(request)
         );
