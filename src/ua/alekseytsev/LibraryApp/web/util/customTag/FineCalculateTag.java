package ua.alekseytsev.LibraryApp.web.util.customTag;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.alekseytsev.LibraryApp.exceptions.LibraryException;
import ua.alekseytsev.LibraryApp.service.FineService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Calculates total price of fine by calculating difference between date of its creation and now
 */
public final class FineCalculateTag extends TagSupport {

    private static final long serialVersionUID = -2448355849373824366L;
    private static final int PRICE = 1;
    private static final Logger LOG = LogManager.getLogger(FineCalculateTag.class);
    private static final int MS_IN_DAY = 1000 * 60 * 60 * 24;
    private Integer fineId;

    public void setFineId(Integer fineId) {
        this.fineId = fineId;
    }

    public int doStartTag() throws JspException {
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            Date rd = sdfDate.parse(String.valueOf(new FineService().getCreatedDate(fineId)));
            int amountDays = (int) ((new Date().getTime() - rd.getTime()) / MS_IN_DAY);
            if (amountDays > 0) {
                pageContext.getOut().write(String.valueOf(amountDays * PRICE));
            }
        } catch (IOException e) {
            LOG.error(e);
            throw new JspTagException(e);
        } catch (ParseException | LibraryException e) {
            LOG.error(e);
        }
        return SKIP_BODY;
    }
}
