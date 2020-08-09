package com.ishapovalov.fun.accountingsystem.servlets;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import io.dropwizard.servlets.assets.AssetServlet;

import java.net.URL;

/**
 * User: ishapovalov
 * Date: 10/25/16 10:58 AM
 */
public final class StaticContentServlet extends AssetServlet {

    private static final String STATIC_ASSETS_FOLDER = "webui";
    private static final String INDEX_FILE = "index.html";

    public StaticContentServlet() {
        super("/" + STATIC_ASSETS_FOLDER, "/", INDEX_FILE, Charsets.UTF_8);
    }

    @Override
    protected URL getResourceUrl(String absoluteRequestedResourcePath) {
        try {
            return Resources.getResource(absoluteRequestedResourcePath);
        } catch (RuntimeException e) {
            return Resources.getResource(STATIC_ASSETS_FOLDER + "/" + INDEX_FILE);
        }
    }
}

