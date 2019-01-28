package br.com.martinlabs.usecase.callback;

import br.com.martinlabs.usecase.model.Principal;
import br.com.martinlabs.usecase.model.WithIdAndTitle;

/**
 * Created by gil on 05/12/17.
 */

public interface PrincipalCallback {
    void call(Principal item);
}
