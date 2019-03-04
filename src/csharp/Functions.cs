
using System;
using System.Linq;
using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Extensions.Http;
using Microsoft.AspNetCore.Http;
using Microsoft.Azure.WebJobs.Host;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using Microsoft.Azure.WebJobs.Extensions.SignalRService;
using System.Collections.Generic;
using System.Security.Claims;

namespace ServerlessDraw
{
    public static class Functions
    {
        [FunctionName("negotiate")]
        public static SignalRConnectionInfo Negotiate(
            [HttpTrigger(AuthorizationLevel.Anonymous, "post")] HttpRequest req,
            [SignalRConnectionInfo(HubName = "serverlessdraw")] SignalRConnectionInfo connectionInfo,
            ILogger log)
        {
            return connectionInfo;
        }

        [FunctionName("draw")]
        public static async Task Draw(
            [HttpTrigger(AuthorizationLevel.Anonymous, "post")] HttpRequest req,
            [SignalR(HubName = "serverlessdraw")] IAsyncCollector<SignalRMessage> messages)
        {
            var json = await req.ReadAsStringAsync();
            var strokes = JsonConvert.DeserializeObject<StrokeCollection>(json);

            await messages.AddAsync(new SignalRMessage
            {
                Target = "newStrokes",
                Arguments = new[] { strokes }
            });
        }

        [FunctionName("clear")]
        public static void ClearCanvas(
            [HttpTrigger(AuthorizationLevel.Anonymous, "post")] HttpRequest req,
            [SignalR(HubName = "serverlessdraw")] out SignalRMessage message,
            ILogger log)
        {
            message = new SignalRMessage {
                Target = "clearCanvas",
                Arguments = new object[0]
            };
        }
    }
}
