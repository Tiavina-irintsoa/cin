using Microsoft.AspNetCore.Mvc;
using banque.Models;
using System.IO;
using System.Text;
namespace banque.Controllers;

[ApiController]
[Route("[controller]")]
public class TransfertController : ControllerBase
{
    [HttpGet("transfert")]
    public async Task<IActionResult> transfert(string sender,string receiver, string montant, string iddevise, string date)
     {       
        Console.WriteLine(sender);
        Console.WriteLine(receiver);
        Console.WriteLine(montant);
        Console.WriteLine(iddevise);
        Console.WriteLine(date);
            try{
                Transfert t = new Transfert(sender,receiver,montant,iddevise,date);
                t.Do();
                return Ok(""); 
            }
            catch(Exception e){
                return Ok(e.Message);
            }
        }
    }
