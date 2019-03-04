using System;
using System.Collections.Generic;

namespace ServerlessDraw
{
    public class StrokeCollection
    {
        public IEnumerable<Stroke> strokes { get; set; }
        public string sender { get; set; }
    }
}
