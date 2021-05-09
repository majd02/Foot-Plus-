<?php

namespace App\Controller;

use App\Entity\Stade;
use App\Form\StadeType;
use App\Repository\StadeRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class MobileController extends AbstractController
{
    /**
     * @Route("/mobile", name="mobile")
     */
    public function index(): Response
    {
        return $this->render('mobile/index.html.twig', [
            'controller_name' => 'MobileController',
        ]);
    }

    /**
     *  @Route("/liste", name="liste")
     * @param StadeRepository $repository
     */
    public function getCours(Request $request,StadeRepository  $stadeRepository,NormalizerInterface $normalizer):Response{
        $repo = $stadeRepository->findAll();

        $json = $normalizer->normalize($repo,'json',['groups'=>'stade']);

        return new Response (json_encode($json));

    }

    /**
     * @Route("/add", name="add_stade")
     */
    public function addStades(Request $request){
        $stade= new Stade();
        $nom= $request->query->get("nom");
        $adresse= $request->query->get("adresse");
        $prixph= $request->query->get("prixph");
        $contact= $request->query->get("contact");
        $image= $request->query->get("image");
        $stade->setNom($nom);
        $stade->setAdresse($adresse);
        $stade->setPrixph($prixph);
        $stade->setContact($contact);
        $stade->setImage($image);
        $em=$this->getDoctrine()->getManager();
        $em->persist($stade);
        $em->flush();
        $serialize = new Serializer([new ObjectNormalizer()]);
        $formatted = $serialize->normalize("Stade Ajoutée");
        return new JsonResponse($formatted);

    }
    /**
     * @Route("/deletestade", name="deletestade", methods={"GET","POST"})
     *
     */
    public function deleteStade(Request $request){
        $id_stade=$request->get('id_stade');
            $entityManager = $this->getDoctrine()->getManager();
            $stade=$entityManager->getRepository(Stade::class)->find($id_stade);
            if($stade!=null){
                $entityManager->remove($stade);
                $entityManager->flush();
                $serialize = new Serializer([new ObjectNormalizer()]);
                $formatted = $serialize->normalize("Stade deleted");
                return new JsonResponse($formatted);
            }

    }
    /**
     * @Route("/updatestade", name="updatestade", methods={"GET","POST"})
     *
     */
    public function updateStade(Request $request){
        $em=$this->getDoctrine()->getManager();
        $stade=$this->getDoctrine()->getManager()->getRepository(Stade::class)->find($request->get("id_stade"));
        $stade->setNom($request->get("nom"));
        $stade->setAdresse($request->get("adresse"));
        $stade->setPrixph($request->get("prixph"));
        $stade->setContact($request->get("contact"));
        $stade->setImage($request->get("image"));
        $em->persist($stade);
        $em->flush();
        $serialize = new Serializer([new ObjectNormalizer()]);
        $formatted = $serialize->normalize("Stade updated");
        return new JsonResponse($formatted);
    }


    /**
     * @Route("/detail/{id_stade}", name="detailstade")
     *
     */



    public function Stadedetail(Request $request,StadeRepository $stadeRepository,SerializerInterface $serializerinterface,$id_stade)
    {
        $repo = $stadeRepository->find($id_stade);
        $json = $serializerinterface->serialize($repo,'json',['groups'=>'stade']);

        return new Response (json_encode($json));

    }

}
