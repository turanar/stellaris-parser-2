package net.turanar.stellaris.domain;

public class WeightModifier extends Modifier {
    public boolean tech_weight_likelihood = false;

    @Override
    public String toString() {
        String format = "(×%s)";
        if(tech_weight_likelihood) format = "%s";
        if(add != null && add > 0) format = "(+%s)";
        if(type != null) format += " %s";

        String s_factor = "";
        if (factor != null && factor>= 1.0f) s_factor = "<b style='color:lime'>" + factor + "</b>";
        if (factor != null && factor < 1.0f) s_factor = "<b style='color:red'>" + factor + "</b>";
        if(tech_weight_likelihood) s_factor =  "(×<b style='color:lime'>1.5</b> / ×<b style='color:lime'>3</b> for Technocracy Civic)<br/>";

        return String.format(format, s_factor, type != null ? type.parse(pair).replaceAll("\\n","<br/>") : "");
    }
}
