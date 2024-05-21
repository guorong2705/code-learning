package com.guorong.lod.v1;

class HtmlDownloader {
    private NetworkTransporter transporter;

    public HtmlDownloader(NetworkTransporter transporter) {
        this.transporter = transporter;
    }

    public HtmlDownloader() {
    }

    public Html downloadHtml(String url) {
        Byte[] rawHtml = transporter.send(new HtmlRequest(url));
        return new Html(rawHtml);
    }

}
