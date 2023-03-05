FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "file://persist-keyfile.conf"

do_install:append() {
	install -Dm 0644 ${WORKDIR}/persist-keyfile.conf ${D}${sysconfdir}/NetworkManager/conf.d/persist-keyfile.conf
}

