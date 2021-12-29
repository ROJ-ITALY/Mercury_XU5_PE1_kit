# add pstree
IMAGE_INSTALL_append = " psmisc"

# add tools to measure latencies in Linux
IMAGE_INSTALL_append = " rt-tests stress-ng"

# add libmetal custom demos (client and server) built via Vitis IDE
IMAGE_INSTALL_append = " libmetal-custom-demos"
