using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using banque.Models;
using RH.Models;
using System.Text.Json;

namespace dotnet.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class BanqueController: ControllerBase{
        [HttpGet("operations/{idcin}")]
        public IActionResult GetOperations(string idcin)
        {
            try{
                FicheBanque fiche = new FicheBanque(idcin);
                string json = JsonConvert.SerializeObject(fiche, Formatting.Indented); 
                return Ok(json);
            }
            catch (Exception ex)
            {
                return BadRequest($"Erreur : {ex.Message}");
            }
        }
    }
}
