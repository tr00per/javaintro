
package sda.code.intermediate.part2.answers.json.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Rain {

    @SerializedName("3h")
    @Expose
    private Integer _3h;

    /**
     * 
     * @return
     *     The _3h
     */
    public Integer get3h() {
        return _3h;
    }

    /**
     * 
     * @param _3h
     *     The 3h
     */
    public void set3h(Integer _3h) {
        this._3h = _3h;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(_3h).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rain) == false) {
            return false;
        }
        Rain rhs = ((Rain) other);
        return new EqualsBuilder().append(_3h, rhs._3h).isEquals();
    }

}
