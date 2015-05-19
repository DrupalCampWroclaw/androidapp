package pl.drupalcampwroclaw.drupalcamp2015.others;

import android.content.Context;
import android.widget.Toast;

import pl.drupalcampwroclaw.drupalcamp2015.R;


public class ErrorMessage {

    // Flag error.
    private int error_no = 0;

    // Context.
    private Context context;

    
    public ErrorMessage(Context context) {
        this.context = context;
    }

    /**
     * Get error number.
     */
    public int getError() {
        return this.error_no;
    }

    /**
     * Set error number.
     */
    public void setError(int error) {
        if (this.error_no == 0) {
            this.error_no = error;
        }
    }

    /**
     * Show error.
     */
    public void showError() {
        if (this.error_no == 0) {
            return;
        }

        // Message error.
        String message = null;
        switch (this.error_no) {
            case 100:
                message = this.context.getString(R.string.error_connect_json);
                break;
            case 200:
                message = this.context.getString(R.string.error_parser_json);
                break;
            case 300:
                message = this.context.getString(R.string.error_load_json);
                break;
            default:
        }

        if (message != null) {
            // Show toast.
            Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Clear error.
     */
    public void clearError() {
        this.error_no = 0;
    }

}
