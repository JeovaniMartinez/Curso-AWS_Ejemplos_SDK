using System;
using System.Collections.Generic;
using System.Windows.Forms;

using Amazon;
using Amazon.SimpleEmail;
using Amazon.SimpleEmail.Model;


namespace aws_net_sdk_example_1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        // Realiza el envió de un correo electrónico mediante AWS, con los valores especificados en los parámetros
        private void sendEmail(String source, String destination, String subject, String content)
        {
            using (var client = new AmazonSimpleEmailServiceClient(RegionEndpoint.USEast1))
            {
                var sendRequest = new SendEmailRequest
                {
                    Source = source,
                    Destination = new Destination
                    {
                        ToAddresses =
                        new List<string> { destination }
                    },
                    Message = new Amazon.SimpleEmail.Model.Message
                    {
                        Subject = new Content(subject),
                        Body = new Body
                        {
                            Text = new Content
                            {
                                Charset = "UTF-8",
                                Data = content
                            }
                        }
                    },
                };
                try
                {
                    var response = client.SendEmail(sendRequest);
                    MessageBox.Show("El correo se ha enviado correctamente");
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
        }

        private void btSend_Click(object sender, EventArgs e)
        {
            sendEmail("contacto@app-notas.com", tbDestination.Text, tbSubject.Text, rtbContent.Text);
        }
    }
}
