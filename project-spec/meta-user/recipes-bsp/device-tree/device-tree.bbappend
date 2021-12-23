FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
PREFIX := "${THISDIR}/files"

SRC_URI += " file://system-user.dtsi"
SRC_URI += " file://zynqmp_enclustra_common.dtsi"
SRC_URI += " file://zynqmp_enclustra_mercury_xu5.dtsi"
SRC_URI += " file://zynqmp_enclustra_mercury_pe1.dtsi"
SRC_URI += " file://xen.dtsi"

addtask copy after do_fetch before do_unpack
do_copy() {	
    if [ "${XEN_DOMU_BAREMETAL}" == "1" ]; then
       cp ${PREFIX}/system-user-passthrough.dtsi ${PREFIX}/system-user.dtsi
    else
       cp ${PREFIX}/system-user-default.dtsi ${PREFIX}/system-user.dtsi
    fi
}

python () {
    if d.getVar("CONFIG_DISABLE"):
        d.setVarFlag("do_configure", "noexec", "1")
}

export PETALINUX
do_configure_append () {
	script="${PETALINUX}/etc/hsm/scripts/petalinux_hsm_bridge.tcl"
	data=${PETALINUX}/etc/hsm/data/
	eval xsct -sdx -nodisp ${script} -c ${WORKDIR}/config \
	-hdf ${DT_FILES_PATH}/hardware_description.${HDF_EXT} -repo ${S} \
	-data ${data} -sw ${DT_FILES_PATH} -o ${DT_FILES_PATH} -a "soc_mapping"
}
