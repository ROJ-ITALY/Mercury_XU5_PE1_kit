PLNX_XEN_DEPLOY = "${@ 'my-image-xlnx-xen' if d.getVar('IMAGE_PLNX_XEN_DEPLOY') == '1' else '' }"
inherit deploy ${PLNX_XEN_DEPLOY}

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://si5338.patch"
SRC_URI += " file://qspi.patch"
